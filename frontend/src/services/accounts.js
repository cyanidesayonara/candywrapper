import axios from 'axios'

const baseUrl = 'api/accounts/'

const login = async (user) => {
  const response = await axios.post(`${baseUrl}login`, user)
  return response.data
}

const signup = async (user) => {
  const response = await axios.post(`${baseUrl}signup`, user)
  return response.data
}

export default { login, signup }