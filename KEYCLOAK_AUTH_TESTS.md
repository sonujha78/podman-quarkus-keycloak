# Keycloak OIDC Authorization Test Evidence

## Setup note

Keycloak realm inventory-realm required disabling "Required field" on the email User Profile attribute, password-grant tokens for users without email/profile data were failing with "Account is not fully set up".

## Test 1: No token, expect 401

curl -i http://localhost:8080/products
curl -i http://localhost:8080/admin

Result: both return 401 Unauthorized with header www-authenticate: Bearer

## Test 2: Valid token, wrong role (normaluser, role=user), expect 403 on /admin

curl -i http://localhost:8080/admin -H "Authorization: Bearer $TOKEN"
curl -i http://localhost:8080/products -H "Authorization: Bearer $TOKEN"

Result:
- /admin -> 403 Forbidden
- /products -> 200 OK (any authenticated user allowed)

## Test 3: Valid token, correct role (adminuser, role=admin), expect 200

curl -i http://localhost:8080/admin -H "Authorization: Bearer $ADMIN_TOKEN"

Result: 200 OK
{"message": "Hello Admin! This is a protected admin-only endpoint."}

## Conclusion

Role-based access control via quarkus-oidc + Keycloak works as required: 401 (no auth) -> 403 (wrong role) -> 200 (correct role), all verified.
