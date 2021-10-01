import axios from 'axios'

const AXIOS = axios.create({
  baseURL: `/api-f`,
  timeout: 1000
});


export default {
    hello() {
        return AXIOS.get(`/hello`);
    },
    getUser(userId) {
        return AXIOS.get(`/user/` + userId);
    },
    createUser(firstName, lastName) {
        return AXIOS.post(`/user/` + firstName + '/' + lastName);
    },
    getSecured(user, password) {
        return AXIOS.get(`/secured/`,{
            auth: {
                username: user,
                password: password
            }});
    },
    addFurniture(furniture) {
        return AXIOS.post('/furniture', {
            name: furniture.name,
            quantity: furniture.quantity,
            description: furniture.description,
            img_url: furniture.img_url,
            price: furniture.price,
        })
    },
    showAllFurniture() {
        return AXIOS.get('/all');
    }
}


