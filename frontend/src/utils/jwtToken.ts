export function storeJwtToken(token: string): void {
    localStorage.setItem("token", token);
}

export function getJwtToken(token: string): string | null {
    const token = localStorage.getItem("token");
    if (!token) return null;

    try {
        return JSON.stringify(token);
    } catch (err) {
        console.error("Couldn't parse jwt token from local storage.");
        return null;
    }
}
