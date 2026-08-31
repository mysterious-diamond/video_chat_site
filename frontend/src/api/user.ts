import { api } from './client.ts'

export const userApi = {
    signin: (name: string, password: string) => api.post<string>("signin", { name, password }),
    signup: (name: string, nickname: string, password: string) => api.post<string>("signup", { name, nickname, password })
}
