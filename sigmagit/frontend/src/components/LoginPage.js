import OAuth2Login from "./library/OAuth2Login"

const onSuccess = (code) => {
    console.log(code);
    return code;
};
const onFailure = (response) => console.error(response);

function LoginPage() {
    return (
        <OAuth2Login
            authorizeUri="https://github.com/login/oauth/authorize"
            responseType="code"
            clientId="d280af0562c1f960a43f"
            redirectUri="http://localhost:8080/auth"
            scope="identify guilds"
            onSuccess={onSuccess}
            onFailure={onFailure}
        />
    )
}

export default LoginPage;
