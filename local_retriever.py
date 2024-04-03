from langchain.chains import RetrievalQA, LLMChain,SequentialChain
from langchain_community.llms.chatglm3 import ChatGLM3
from langchain.chains.router import MultiRetrievalQAChain
from langchain.prompts import PromptTemplate
from langchain_community.vectorstores.faiss import FAISS
from langchain_community.embeddings.huggingface import HuggingFaceEmbeddings
from langchain_community.utilities.google_serper import GoogleSerperAPIWrapper
import os
from langchain.agents import Tool, AgentExecutor,create_react_agent,create_openai_tools_agent
from langchain.memory import ConversationBufferMemory
from langchain.tools.retriever import create_retriever_tool
import config
from langchain_community.tools.tavily_search import TavilySearchResults
from template import RETRIEVE_TEMPLATE,FORMAT_INSTRUCTIONS,PREFIX,SUFFIX,TEMPLATE
from langchain_community.tools.convert_to_openai import format_tool_to_openai_tool
from test import get_glm
import time
from langchain_openai import ChatOpenAI
from langchain import hub

#from langchain.agents.mrkl.prompt import PREFIX, SUFFIX
#google_serp_api_key = "8090c2b254c44fd16eef421a17761cd9e7bfed49"

#local retriever and retrieve tool
embedding_path="./bge-large-zh-v1.5"
embeddings = HuggingFaceEmbeddings(model_name=embedding_path)
vectorstore = FAISS.load_local("wuhan_vector_202431", embeddings)
retriever = vectorstore.as_retriever()
retriever_tool = create_retriever_tool(
    retriever = retriever,
    name= "local_retriever",
    description= "使用这个工具来检索本地的文档"
)

tavily_search_tool = TavilySearchResults(max_results=1)

search = GoogleSerperAPIWrapper()
google_search_tool = Tool(
        name="Google_Search",
        func= search.run,
        description="当用户提出你不知道的问题时,请使用这个工具来联网检索."
)
#tools
tools = [google_search_tool]

#
now_time = time.asctime()


#llm- llm chain agnet
llm = ChatOpenAI(model="gpt-3.5-turbo-0125", temperature=0)
#llm = get_glm(0.8)
prompt = hub.pull("hwchase17/openai-tools-agent")
agent = create_openai_tools_agent(llm=llm,tools=tools,prompt=prompt)
agent_executor = AgentExecutor(agent=agent,tools=tools,verbose=True)
res = agent_executor.invoke({"input":"搜索出武汉本地价格很高的酒店,给出人均价格等信息"})
print(res)







