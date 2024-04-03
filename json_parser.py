from langchain.output_parsers.openai_tools import JsonOutputToolsParser
from langchain_core.output_parsers import JsonOutputParser
from langchain.prompts import PromptTemplate
from langchain_openai import ChatOpenAI
import config
import os

from typing import List,Dict
from langchain.prompts import PromptTemplate
from langchain_core.output_parsers import JsonOutputParser
from langchain_core.pydantic_v1 import BaseModel, Field
from langchain_openai import ChatOpenAI

class PlanNode(BaseModel):
    """日计划中的node，一个node就是一个具体的地方"""
    place:str = Field(description="规划路线中的具体位置，比如具体的酒店、饭店、地铁站、景点、等等")
    time:str = Field(description="对应于place中具体地点的时间段，比如9:00-12:00")
    Type:str = Field(description="这个node的类型，比如是酒店、饭店、景点还是交通等")
    description:str = Field(description="这个节点的一般性描述")
    tag:List[str] = Field(description="这个节点的标签")
    budget:float = Field(description="这个节点的大致花费")
    telephone:str = Field(description="这个节点的具体地点的联系方式，如果没有则为空串")
    
class DayPlan(BaseModel):
    """日计划，由很多个node组成"""
    nodes:Dict[str,PlanNode] = Field(description="一天的计划的各个节点")
    
class TripPlan(BaseModel):
    """整个计划，由几个日计划组成"""
    days:Dict[str,DayPlan] = Field(description="整个旅行计划，包含了每天的计划")


llm = ChatOpenAI()
parser = JsonOutputParser(pydantic_object=TripPlan)
prompt = PromptTemplate(
    template=" 帮助用户做出规划。包括景点、饭店、住宿、交通和其他五个方面。请注意，住宿和吃饭和交通也是规划中重要的几个方面。\n{format_instructions}\n{query}\n",
    input_variables=["query"],
    partial_variables={"format_instructions": parser.get_format_instructions()},
)
chain = prompt | llm | parser
res = chain.invoke({"query":"到武汉玩3天,预算1000元"})
print(res)