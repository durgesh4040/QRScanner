export function parseJwt(token) {
  if (!token) {
    throw new Error("Token is missing");
  }

  const parts = token.split(".");
  if (parts.length !== 3) {
    throw new Error("Invalid token format");
  }

  const payload = parts[1];
  try {
    const decodedPayload = atob(payload.replace(/_/g, "/").replace(/-/g, "+"));
    const parsedPayload = JSON.parse(decodedPayload);
    return parsedPayload;
  } catch (error) {
    throw new Error("Failed to parse token payload");
  }
}
