

export const setJWT = (state, jwt) => {
    state.jwt = jwt;
    state.authorized = jwt != '' && jwt != null && jwt != undefined;
};
