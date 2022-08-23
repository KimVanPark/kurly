import api from "@/api/index";

const fetchWorker = async (params) => {
  try {
    const {data} = await api.request({
      method: "get",
      url: "/v1/workers",
      params: params
    })
    return data;
  } catch (e) {
    throw Error(e)
  }
}

export default {
  fetchWorker
}
