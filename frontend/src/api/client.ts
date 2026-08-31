import { BACKEND_URL } from "../env";

async function requestBackend<T>(path: String, options?: RequestInit): Promise<T> {
    const response = await fetch(`${BACKEND_URL}${path}`, {
        headers: {
            "Content-Type": "application/json",
            ...((options?.headers as Record<string, string>) || {}),
        },
        ...options,
    });

    if (!response.ok) {
        throw new Error(`HTTP ${response.status}: ${await response.text()}`);
    }

    return response.json() as Promise<T>;
}

export const api = {
    get: <T>(path: String) => requestBackend<T>(path, { method: "GET" }),
    post: <T>(path: String, body: unknown) => requestBackend<T>(path, { method: "POST", body: JSON.stringify(body) })
}
