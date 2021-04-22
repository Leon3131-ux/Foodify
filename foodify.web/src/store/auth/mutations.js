

export const setJWT = (state, jwt) => {
    state.jwt = jwt;
    console.log(state.jwt)
    state.authorized = jwt != '' && jwt != null && jwt != undefined;
};
