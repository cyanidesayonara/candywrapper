import axios from 'axios'

const baseUrl = 'api/users/'

let token = null

const setToken = newToken => {
  token = `bearer ${newToken}`
}

const getAll = async () => {
  const response = await axios.get(baseUrl)
  return response.data
}

const getOne = async (user) => {
  const response = await axios.get(`${baseUrl}${user.id}`, user)
  return response.data
}

const create = async (user) => {
  const config = {
    headers: { Authorization: token },
  }

  const response = await axios.post(baseUrl, user, config)
  return response.data
}

const update = async (user) => {
  const config = {
    headers: { Authorization: token },
  }

  const response = await axios.put(`${baseUrl}${user.id}`, user, config)
  return response.data
}

const remove = async (user) => {
  const config = {
    headers: { Authorization: token },
  }

  const response = await axios.delete(`${baseUrl}${user.id}`, config)
  return response.data
}

export default { setToken, getAll, getOne, create, update, remove }