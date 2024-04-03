import os
import config
from langchain_openai import OpenAI,ChatOpenAI
from langchain_community.utilities.google_serper import GoogleSerperAPIWrapper
from langchain.agents import Tool
from langchain.agents import create_openai_tools_agent,create_react_agent,load_tools
from langchain.agents import AgentExecutor
from langchain_community.tools.tavily_search import TavilySearchResults
from langchain import hub
import requests


def get_weather(city):
        #city参数是城市的adcode编码
        url = "https://restapi.amap.com/v3/weather/weatherInfo?parameters"
        params = {
                "city":city,
                "key":config.GAODE_API_KEY,
                "extensions":"all"
        }
        res = requests.get(url=url,params=params).text
        return res


print(get_weather(420106))



