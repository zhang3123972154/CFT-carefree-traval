from langchain_core.runnables import RunnableParallel, RunnablePassthrough
from langchain.embeddings.huggingface import HuggingFaceEmbeddings
import csv_loader
import ChatGLM4
from langchain.vectorstores import FAISS

# replace to your model path
# embedding_path = "D:\\ai\\download\\text2vec-large-chinese"
embedding_path="C:\\Users\\31239\\Desktop\\bge-large-zh-v1.5"
embeddings = HuggingFaceEmbeddings(model_name=embedding_path)
cls="Wuhan_Culture Media"
filePath = 'wuhanminist\\{}'.format(cls)
data,vectorstore=csv_loader.CSV_Loader(filePath,isvectorstore=True)

vectorstore.save_local("wuhan_vector_index_new/{}".format(cls))

vectorstore = FAISS.load_local("wuhan_vector_index_new/{}".format(cls), embeddings)
print(vectorstore.docstore._dict)