# Rootless Podman: Security Evidence

## Proof commands and output
- `podman inspect inventory-service --format '{{.HostConfig.Privileged}}'` -> false
- `podman info --format '{{.Host.Security.Rootless}}'` -> true
- Container process inside: `./application` runs as UID 101000 (not 0/root)
- Host user running podman: `sonu` (UID 1000, non-root)

## Why rootless Podman is more secure than Docker's default
- Docker runs a background daemon (`dockerd`) as root at all times — a
  vulnerability in that daemon gives an attacker root on the host.
- Podman has no daemon. Each container is a direct child process of the
  user who ran `podman run`, launched via fork/exec.
- Rootless Podman uses Linux user namespaces: inside the container the
  process may see itself as UID 0, but it's mapped to an unprivileged
  UID (e.g. 101000) on the host, as shown above. Even a full container
  breakout does not grant real root on the host.
- Smaller attack surface: no always-on root daemon listening on a
  socket that other processes/users could potentially reach.
