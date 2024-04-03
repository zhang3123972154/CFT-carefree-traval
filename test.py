import time
import jwt
from langchain_openai import ChatOpenAI
from langchain_community.tools.convert_to_openai import format_tool_to_openai_tool

def generate_token(apikey: str, exp_seconds: int):
    try:
        id, secret = apikey.split(".")
    except Exception as e:
        raise Exception("invalid apikey", e)
 
    payload = {
        "api_key": id,
        "exp": int(round(time.time() * 1000)) + exp_seconds * 1000,
        "timestamp": int(round(time.time() * 1000)),
    }
 
    return jwt.encode(
        payload,
        secret,
        algorithm="HS256",
        headers={"alg": "HS256", "sign_type": "SIGN"},
    )
    
def get_glm(temprature):
    llm = ChatOpenAI(
        model_name="glm-4",
        openai_api_base="https://open.bigmodel.cn/api/paas/v4",
        openai_api_key=generate_token(apikey="1de528d9f1a2e8f40cfe317c64c398a7.3k9l5YXLIgbBmv6O",exp_seconds=100),
        streaming=False,
        temperature=temprature
    )
    return llm

# llm = get_glm(0.01)
# print(llm.invoke("我想在夏天去自驾游，从南昌开往海南，请你帮我规划具体路线，包括住宿酒店、用餐和大致的费用等信息"))


