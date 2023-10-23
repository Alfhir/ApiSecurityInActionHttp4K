# ApiSecInActionHttp4k

I am reading API Security on Action by Neil Madden and I want to code along to make things stick a bit better.
Also, I figured it would be fun doing this with Http4K.

## Roadmap

### Thread modelling with STRIDE

// TODO

### General Scheme
  1. Rate limiting (at load-balancer / reverse proxy / API gateway, but opt. also  per server for defense in depth)
  2. Authentication (various methods, do a branch per chapter)
  3. Audit logging (simple first, maybe explore OT and Honeycomb later)
  4. Access control

### Code along the books chapters and create a...

- [ ] API with RDBMS storage
  - [ ] Injection
  - [ ] Permissions
  - [ ] Input validation
  - [ ] Deserialization (https://cheatsheetseries.owasp.org/cheatsheets/Deserialization_Cheat_Sheet.html#other-deserialization-libraries-and-formats)
  - [ ] Safe output / XSS (esp. when returning HTML)
  - [ ] Basic Auth
  - [ ] Safe PW storage
  - [ ] Encryption / HTTPS / Strict transport security
  - [ ] Simple audit logging in DB / maybe use SIEM like Honeycomb
  - [ ] ACL in DB (avoid privilege escalation attacks)
- [ ] Session cookie Auth
  - [ ] Session cookies 
  - [ ] Avoiding session fixation
  - [ ] Cookie security attributes
  - [ ] CSRF (SameSite cookies and Hash based double submit pattern)
  - [ ] Timing attacks 
- [ ] Modern token-based Auth
  - // TODO
- [ ] Self-contained tokens and JWTs
  - // TODO
- [ ] OAuth & OpenID Connect
  - // TODO
- [ ] Identity based access control
  - // TODO
- [ ]  Capability based access control and macaroons
  - // TODO
- [ ] K8s
  - // TODO
- [ ] Securing Service-to-Service APIs
  - // TODO
- [x] Securing IOT
  - // Not doing that


### Package

```
./gradlew jib
```

More details on building JIB can be found at [https://github.com/GoogleContainerTools/jib/tree/master/jib-gradle-plugin]()

