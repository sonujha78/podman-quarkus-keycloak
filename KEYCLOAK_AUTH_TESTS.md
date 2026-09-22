# Keycloak OIDC Authorization Test Evidence

## Setup notes
- Keycloak realm "inventory-realm" required disabling "Required field" on
  the `email` User Profile attribute, since password-grant tokens for
  users without email/profile data failed with "Account is not fully set up".

## Test 1: No token
curl -i http://localhost:8080/products
curl -i http://localhost:8080/admin
-> Both return 401 Unauthorized (www-authenticate: Bearer)

## Test 2: Valid token, wrong role (normaluser, role=user)
curl -i http://localhost:8080/admin    -> 403 Forbidden
curl -i http://localhost:8080/products -> 200 OK (any authenticated user allowed)

## Test 3: Valid token, correct role (adminuser, role=admin)
curl -i http://localhost:8080/admin
-> 200 OK
   {"message": "Hello Admin! This is a protected admin-only endpoint."}

## Conclusion
Role-based access control via quarkus-oidc + Keycloak works as required:
401 (no auth) -> 403 (wrong role) -> 200 (correct role), all verified.
