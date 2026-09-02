import { api } from './client.ts'

export const userApi = {
    signin: (name: string, password: string) => api.post<void>("/user/signin", { name, password }),
    signup: (name: string, nickname: string, password: string) => api.post<void>("/user/signup", { name, nickname, password })
}
