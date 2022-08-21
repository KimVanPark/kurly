import api from "@/api/index";

const fetchPickingOrder = async (params) => {
  try {
    const {data} = await api.request({
      method: "get",
      url: "/picking-orders/sample",
      params: params
    })
    return data;
  } catch (e) {
    throw Error(e)
  }
}

const postPicking = async (formData) => {
  console.log(formData)
  try {
    const {data} = await api.request({
      method: "post",
      url: "/picking-orders/picking",
      data: formData
    })
    return data;
  } catch (e) {
    throw Error(e)
  }
}

export default {
  fetchPickingOrder,
  postPicking
}
