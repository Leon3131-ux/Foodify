export const login = (context, obj) => {
    context.commit("setJWT", obj.jwt);
    axios.defaults.headers['authorization'] = "Bearer " + obj.jwt;
};
export const logout = (context) => {
    context.commit("setJWT", "");
    axios.defaults.headers['authorization'] = null;
};




