

export const setJWT = (state, jwt) => {
    console.log("setjwt")
    state.jwt = jwt;
    console.log(state.jwt)
    state.authorized = jwt != '' && jwt != null && jwt != undefined;
};
