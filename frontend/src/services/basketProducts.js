import axios from 'axios'

const baseUrl = 'api/basketproducts/'

const getAll = async () => {
  const response = await axios.get(baseUrl)
  return response.data
}

const getOne = async (basketProduct) => {
  const response = await axios.get(`${baseUrl}${basketProduct.id}`, basketProduct)
  return response.data
}

const create = async (basketProduct) => {
  const response = await axios.post(baseUrl, basketProduct)
  return response.data
}

const update = async (basketProduct) => {
  const response = await axios.put(`${baseUrl}${basketProduct.id}`, basketProduct)
  return response.data
}

const remove = async (basketProduct) => {
  const response = await axios.delete(`${baseUrl}${basketProduct.id}`)
  return response.data
}

export default { getAll, getOne, create, update, remove }