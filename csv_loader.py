import streamlit
from langchain.document_loaders.csv_loader import CSVLoader
import os

from langchain.embeddings import HuggingFaceEmbeddings
from langchain.embeddings.openai import OpenAIEmbeddings
from langchain.text_splitter import CharacterTextSplitter
from langchain.vectorstores import FAISS
from langchain.document_loaders import TextLoader


embedding_path="C:\\Users\\31239\\Desktop\\bge-large-zh-v1.5"
embeddings = HuggingFaceEmbeddings(model_name=embedding_path)

def CSV_Loader(filePath,isvectorstore):

        filenamelist=os.listdir(filePath)
        print(filenamelist)
        datas=[]
        vector=[]
        tag=0
        for intex ,i in enumerate(filenamelist):
           file_path = './{}/{}'.format(filePath,i)
           print(file_path)
           loader = CSVLoader(file_path=file_path)
           data=loader.load()
           if isvectorstore ==True:
               chunks = [data[i:i + 200] for i in range(0, len(data), 200)]
               # Initialize an empty vectorstore
               vectorstore=[]
               # Loop over the chunks and embed them into vectorstore
               for j,chunk in enumerate(chunks):
                   print("*********{}_第{}份 ,加载中.......".format(i, j))
                   # Create a temporary vectorstore from the chunk
                   temp = FAISS.from_documents(chunk, embeddings)
                   print("*********{}_第{}份".format(i,j),temp.docstore._dict)
                   # Merge the temporary vectorstore into the main one
                   if j==0:
                       vectorstore=temp
                   else:
                       vectorstore.merge_from(temp)
               vectorstore.save_local("tempsave/{}/{}".format(filePath,i))
               #print(vectorstore.docstore._dict)
               if intex==0:
                   vector=vectorstore
               else:
                   vector.merge_from(vectorstore)
           datas=datas+data
        #print(datas)

        return datas,vector


def splitter(chunksize,chunkoverlap,datas):
    text_splitter = CharacterTextSplitter(
        separator="  ",
        chunk_size=chunksize,
        chunk_overlap=chunkoverlap,
        length_function=len,
    )

    texts = text_splitter.create_documents(datas)
    return texts




#filePath = 'wuhan_spidar'
#datas=CSV_Loader(filePath)
#texts=splitter(100,10,str(datas))
#print(texts[0])
