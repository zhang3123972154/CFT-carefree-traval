from langchain.output_parsers.openai_tools import JsonOutputToolsParser
from langchain_core.output_parsers import JsonOutputParser
from langchain.prompts import PromptTemplate
from langchain_openai import ChatOpenAI
import config
import os

llm = ChatOpenAI()

parser = JsonOutputParser()

prompt = PromptTemplate(
    template="你是旅行规划助手，请你用中文回答用户问题.\n{format_instructions}\n{query}\n",
    input_variables=["query"],
    partial_variables={"format_instructions": parser.get_format_instructions()},
)

chain = prompt | llm | parser
res = chain.invoke({"query":"""规划一份在武汉本地玩3天的旅程,格式是:{  
  //规划
  day1: {
   //第一天
   node1: {
    place:  ,  
    time: ,
    type: ,
    description: "",
    tag: [tag1,tag2,...],
    budget: ,   
    telephone:   
  },
   node2: {
      time: ,
      ...
    },
   node3: {
      ...
    }
 },
 day2: {
    ...
  },
 day3: {
    ...
  }
}"""
}
                   )
print(res)

