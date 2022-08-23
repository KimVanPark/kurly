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

const assignmentPickingOrder = async (formData) => {
  try {
    const {data} = await api.request({
      method: "post",
      url: "/v1/picking-orders/assignment",
      data: formData
    })
    return data;
  } catch (e) {
    throw Error(e)
  }
}

const postPicking = async (pickingOrderId, pickingOrderItemId, formData) => {
  try {
    const {data} = await api.request({
      method: "post",
      url: `/v1/picking-orders/${pickingOrderId}/items/${pickingOrderItemId}/picking`,
      data: formData
    })
    return data;
  } catch (e) {
    throw Error(e)
  }
}

export default {
  fetchPickingOrder,
  assignmentPickingOrder,
  postPicking
}
