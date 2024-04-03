from langchain.chains import RetrievalQA, LLMChain
from langchain.chains.router import MultiRetrievalQAChain
from langchain.prompts import PromptTemplate
from langchain_community.vectorstores.faiss import FAISS
from langchain_community.embeddings.huggingface import HuggingFaceEmbeddings
import ChatGLM4
from langchain.memory import ConversationBufferMemory
from langchain.memory import SQLChatMessageHistory
from langchain_community.utilities.google_serper import GoogleSerperAPIWrapper
from langchain_community.utilities.bing_search import BingSearchAPIWrapper
import os
from langchain.agents import Tool, ZeroShotAgent
# replace to your model path
google_serp_api_key = "8090c2b254c44fd16eef421a17761cd9e7bfed49"

#local retriever
embedding_path="./bge-large-zh-v1.5"
embeddings = HuggingFaceEmbeddings(model_name=embedding_path)
vectorstore = FAISS.load_local("wuhan_vector_202431", embeddings)
retriever = vectorstore.as_retriever()

#google search api
search = GoogleSerperAPIWrapper(serper_api_key=google_serp_api_key)
tools = [
    Tool(
        name="Search",
        func=search.run,
        description="you can search on the Internet when there is no info about query in database."
    )
]





