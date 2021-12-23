# alarm and login study
- 메시지 지향 미들웨어(MOM) study
- Firebase study
- concurrentSession login study
- OAuth2 login study

## OAuth2 login study
1. 인증이 정상적으로 처리되면 접근할 수 있는 Controller 를 작성한다.
2. API 접근이 정상적으로 되는지 확인 후 Security 설정을 한다. 
3. 개발자 센터에 잘급해준 id 와 secret, 인증서버로부터 callback 을 받을 uri 를 지정해준다. 
4. OAuth2 인증을 완료하고 받은 데이터로 우리의 서비스에 접근할 수 있도록 인증 정보를 생성해주는 서비스를 작성한다. 
5. 토큰을 발급하고 검증할 수 있는 컴포넌트를 작성한다. 
6. OAuth2 로그인 성공 핸들러에서 토큰을 생성 후 response header 에 추가해서 보내준다.
7. Security 설정에 OAuth2 로그인을 활성화하고 앞서 만든 서비스와 인증이 성공하면 처리할 Handler 를 등록한다. 
8. 프로젝트 실행 후 /oauth2/authorization/kakao 로 접근하면 네이버 로그인을 시도하고, 인증이 되면 토큰을 발급해주는 것을 확인할 수 있다. 
9. 발급받은 토큰을 이용하여 Security 인증을 처리하는 필터를 만들어준다.
10. UsernamePasswordAuthenticationFilter 필터 앞에 마든 JwtAuthFilter 를 등록한다. + 토큰이 만료되었을 경우 Refresh 요청을 하기 위한 endpoint 를 만든다.