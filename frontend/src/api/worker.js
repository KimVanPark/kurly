import api from "@/api/index";

const fetchHigh = async (params) => {
  try {
    const {data} = await api.request({
      method: "get",
      url: "/workers/high",
      params: params
    })
    return data;
  } catch (e) {
    console.error("error!!")
  }
}

const fetchLow = async (params) => {
  try {
    const {data} = await api.request({
      method: "get",
      url: "/workers/low",
      params: params
    })
    return data;
  } catch (e) {
    console.error("error!!")
  }
}


const fetchNone = async (params) => {
  try {
    const {data} = await api.request({
      method: "get",
      url: "/workers/none",
      params: params
    })
    return data;
  } catch (e) {
    throw Error(e)
  }
}

export default {
  fetchHigh,
  fetchLow,
  fetchNone
}
