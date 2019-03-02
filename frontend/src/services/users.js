import axios from 'axios'

const baseUrl = 'api/users/'

const login = async (user) => {
  const response = await axios.post(`${baseUrl}/login`, user)
  return response.data
}

const register = async (user) => {
  const response = await axios.post(`${baseUrl}/register`, user)
  return response.data
}

const getAll = async () => {
  const response = await axios.get(baseUrl)
  return response.data
}

const getOne = async (user) => {
  const response = await axios.get(`${baseUrl}/${user.id}`, user)
  return response.data
}

const create = async (user) => {
  const response = await axios.post(baseUrl, user)
  return response.data
}

const update = async (user) => {
  const response = await axios.put(`${baseUrl}/${user.id}`, user)
  return response.data
}

const remove = async (user) => {
  const response = await axios.delete(`${baseUrl}/${user.id}`)
  return response.data
}

export default { login, register, getAll, getOne, create, update, remove }