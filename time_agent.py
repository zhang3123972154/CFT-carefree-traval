import os
import config
from template import RETRIEVE_TEMPLATE,FORMAT_INSTRUCTIONS,PREFIX,SUFFIX,TEMPLATE
from langchain.prompts import PromptTemplate
from langchain.chains import LLMChain,SequentialChain
from template import KEY_WORD_TEMPLATE,G_TEMPLATE,H_TEMPLATE,R_TEMPLATE,R_PREFIX,R_SUFFIX,R_FORMAT_INSTRUCTIONS
from langchain_community.utilities.google_serper import GoogleSerperAPIWrapper
from langchain.agents import Tool,create_react_agent,AgentExecutor,load_tools,initialize_agent,tool,AgentType
from test import get_glm
from datetime import date
from langchain_openai import OpenAI
from langchain import hub




llm = OpenAI()

tools = load_tools(["llm-math", "wikipedia"], llm=llm)
#agent = initialize_agent(tools,llm,agent=AgentType.CHAT_ZERO_SHOT_REACT_DESCRIPTION,handle_parsing_errors=True,verbose=True)

@tool
def get_today(text: str) -> str:
    """返回今天的日期，将其用于与今天的日期相关的任何问题。
    输入应始终为空字符串, 此函数将始终返回今天的date,任何日期的数学运算都应在此函数之外发生"""
    return str(date.today())

prompt = hub.pull("hwchase17/react")

agent1 = create_react_agent(llm=llm,tools=tools+[get_today],prompt=prompt)
agent_executor = AgentExecutor(agent=agent1,tools=tools+[get_today],verbose=True)
try:
    result = agent_executor.invoke({"input":"今天是几号？"})
    print(result)
except:
    print("exception on external access")