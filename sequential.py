import os
import config
from template import RETRIEVE_TEMPLATE,FORMAT_INSTRUCTIONS,PREFIX,SUFFIX,TEMPLATE
from langchain.prompts import PromptTemplate
from langchain.chains import LLMChain,SequentialChain
from template import KEY_WORD_TEMPLATE,G_TEMPLATE,H_TEMPLATE,R_TEMPLATE,R_PREFIX,R_SUFFIX,R_FORMAT_INSTRUCTIONS
from langchain_community.utilities.google_serper import GoogleSerperAPIWrapper
from langchain.agents import Tool,create_react_agent,AgentExecutor
from test import get_glm
from langchain_community.tools.tavily_search import TavilySearchResults
from langchain_openai import OpenAI,ChatOpenAI
from langchain.memory import ConversationBufferMemory

#
search = GoogleSerperAPIWrapper()
google_search_tool = Tool(
        name="Google_Search",
        func= search.run,
        description="当用户提出你不知道的问题时,请使用这个工具来联网检索."
)
tavily_search_tool = TavilySearchResults(max_results=1)
tools = [google_search_tool]

#
llm = ChatOpenAI(model="gpt-3.5-turbo-0125", temperature=0)
#
memory = ConversationBufferMemory(memory_key="chat_history")
#
template1 = KEY_WORD_TEMPLATE
prompt1 = PromptTemplate(template=template1,input_variables=["input"])
key_word_chain = LLMChain(llm=llm,prompt=prompt1,output_key="key_word")
#
template2 = G_TEMPLATE
prompt2 = PromptTemplate(template=template2,input_variables=["key_word","weather"])
g_chain = LLMChain(llm=llm,prompt=prompt2,output_key="plan")
#
template3 = H_TEMPLATE
prompt3 = PromptTemplate(template=template3,input_variables=["plan"])
h_chain = LLMChain(llm=llm,prompt=prompt3,output_key="h_plan")
#
template4 = "\n\n".join([R_PREFIX,"{tools}",R_FORMAT_INSTRUCTIONS,R_SUFFIX])
prompt4 = PromptTemplate(template=template4,input_variables=["key_word"])
agent = create_react_agent(llm=llm,tools=tools,prompt=prompt4)
agent_executor = AgentExecutor(agent=agent,tools=tools,verbose=True)
#
sequential_chain = SequentialChain(
    chains=[key_word_chain,g_chain,h_chain],
    input_variables=["input","weather"],
    output_variables=["key_word","plan","h_plan"],
    verbose=True,
)
question = {
    "input":"3月25日到3月28日到武汉武昌区周边玩三天,预算1000",
    "weather":"天气晴朗,气温15摄氏度到20摄氏度"
}
res = sequential_chain.invoke(question)
print(res)




