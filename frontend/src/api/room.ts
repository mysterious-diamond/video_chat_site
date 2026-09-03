import { api } from './client.ts'

export const roomApi = {
    getRooms: () => api.get<string>('/rooms')
}
