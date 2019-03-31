import axios from 'axios'


const baseUrl = 'api/basketproducts/'
let token = null

const setToken = newToken => {
  token = `bearer ${newToken}`
}

const getAll = async () => {
  const response = await axios.get(baseUrl)
  return response.data
}

const getOne = async (basketProduct) => {
  const response = await axios.get(`${baseUrl}${basketProduct.id}`, basketProduct)
  return response.data
}

const create = async (basketProduct) => {
  const config = {
    headers: { Authorization: token },
  }

  const response = await axios.post(baseUrl, basketProduct, config)
  return response.data
}

const update = async (basketProduct) => {
  const config = {
    headers: { Authorization: token },
  }

  const response = await axios.put(`${baseUrl}${basketProduct.id}`, basketProduct, config)
  return response.data
}

const remove = async (basketProduct) => {
  const config = {
    headers: { Authorization: token },
  }

  const response = await axios.delete(`${baseUrl}${basketProduct.id}`, config)
  return response.data
}

export default { setToken, getAll, getOne, create, update, remove }