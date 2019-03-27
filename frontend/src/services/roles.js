import axios from 'axios'

const baseUrl = 'api/roles/'

let token = null

const setToken = newToken => {
  token = `bearer ${newToken}`
}

const getAll = async () => {
  const response = await axios.get(baseUrl)
  return response.data
}

const getOne = async (role) => {
  const response = await axios.get(`${baseUrl}${role.id}`, role)
  return response.data
}

const create = async (role) => {
  const config = {
    headers: { Authorization: token },
  }

  const response = await axios.post(baseUrl, role, config)
  return response.data
}

const update = async (role) => {
  const config = {
    headers: { Authorization: token },
  }

  const response = await axios.put(`${baseUrl}${role.id}`, role, config)
  return response.data
}

const remove = async (role) => {
  const config = {
    headers: { Authorization: token },
  }

  const response = await axios.delete(`${baseUrl}${role.id}`, config)
  return response.data
}

export default { setToken, getAll, getOne, create, update, remove }