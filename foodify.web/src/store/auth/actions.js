export const login = (context, obj) => {
    context.commit("setJWT", obj.jwt);
    delete axios.defaults.headers['authorization'];
    axios.defaults.headers['authorization'] = "Bearer " + obj.jwt;
};
export const logout = (context) => {
    context.commit("setJWT", "");
    axios.defaults.headers['authorization'] = null;
};




