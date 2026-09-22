# Keycloak Setup

- Realm: inventory-realm
- Client: inventory-service (confidential, client authentication ON)
- Client secret: zwaDJaHiuw5udO6w0oBI1stCEVVLOSVA
- Realm roles: admin, user
- Users:
  - adminuser / password: admin123 / role: admin
  - normaluser / password: user123 / role: user

## Run Keycloak

podman run -d --name keycloak --network host -e KEYCLOAK_ADMIN=admin -e KEYCLOAK_ADMIN_PASSWORD=admin -e KC_HTTP_PORT=8081 quay.io/keycloak/keycloak:26.0 start-dev

Admin console: http://localhost:8081
