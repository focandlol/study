import streamlit as st
from dotenv import load_dotenv
from langchain_openai import OpenAIEmbeddings
from langchain_pinecone import PineconeVectorStore
from langchain_openai import ChatOpenAI
from langchain import hub
from langchain.chains import RetrievalQA
from langchain_core.output_parsers import StrOutputParser
from langchain_core.prompts import ChatPromptTemplate
from langchain.chains import create_retrieval_chain
from langchain.chains.combine_documents import create_stuff_documents_chain
from langchain_core.runnables import RunnableLambda

st.set_page_config(page_title='챗봇')

st.title("챗봇")
st.caption('소득세에 관련된 모든것')

load_dotenv()
def get_ai_message(user_message):

    # OpenAI에서 제공하는 Embedding Model을 활용해서 `chunk`를 vector화
    embedding = OpenAIEmbeddings(model='text-embedding-3-large')


    index_name = 'tax-index'

    database = PineconeVectorStore.from_existing_index(index_name, embedding)

    llm = ChatOpenAI(model='gpt-4o')
    # prompt = hub.pull("rlm/rag-prompt")

    prompt = ChatPromptTemplate.from_template("""
        You are an assistant for question-answering tasks.
        Use the following pieces of retrieved context to answer the question.
        If you don't know the answer, just say that you don't know.
        Use three sentences maximum and keep the answer concise.

        Question: {input}
        Context: {context}
        Answer:
    """)

    system_prompt = ()

    retriever = database.as_retriever()
    # qa_chain = RetrievalQA.from_chain_type(
    #     llm, 
    #     retriever=retriever,
    #     chain_type_kwargs={"prompt": prompt}
    # )

    question_answer_chain = create_stuff_documents_chain(llm, prompt)

    qa_chain = create_retrieval_chain(retriever, question_answer_chain)

    dictionary = ["사람을 나타내는 표현 -> 거주자"]
    prompt = ChatPromptTemplate.from_template(f"""
        사용자의 질문을 보고, 우리의 사전을 참고해서 사용자의 질문을 변경해주세요.
        만약 변경할 필요가 없다고 판단된다면, 사용자의 질문을 변경하지 않아도 됩니다.
        그런 경우에는 질문만 리턴해주세요.
        사전 : {dictionary}

        질문 : {{input}}                           
    """)

    dictionary_chain = prompt | llm | StrOutputParser()

    tax_chain = {"input": dictionary_chain} | qa_chain
    ai_response = tax_chain.invoke({"input": user_message})
    # ai_response = qa_chain.invoke({"input": user_message})
    return ai_response


if 'message_list' not in st.session_state:
    st.session_state.message_list = []

for message in st.session_state.message_list:
    with st.chat_message(message['role']):
        st.write(message['content'])

if user_question := st.chat_input(placeholder='궁금한 것'):
    with st.chat_message('user'):
        st.write(user_question)
    st.session_state.message_list.append({"role":"user", "content":user_question})

    ai_message = get_ai_message(user_question)

    with st.chat_message('ai'):
        st.write(ai_message)
    st.session_state.message_list.append({"role":"ai", "content":ai_message})