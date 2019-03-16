import axios from 'axios'

const baseUrl = 'api/products/'

const getAll = async () => {
  const response = await axios.get(baseUrl)
  return response.data
}

const getOne = async (product) => {
  const response = await axios.get(`${baseUrl}${product.id}`, product)
  return response.data
}

const create = async (product) => {
  const response = await axios.post(baseUrl, product)
  return response.data
}

const update = async (product) => {
  const response = await axios.put(`${baseUrl}${product.id}`, product)
  return response.data
}

const remove = async (product) => {
  const response = await axios.delete(`${baseUrl}${product.id}`)
  return response.data
}

export default { getAll, getOne, create, update, remove }