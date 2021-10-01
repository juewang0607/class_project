<template>
    <div class="publish-furniture">
        <input type="text" v-model="furniture.name" placeholder="name">
        <input type="text" v-model="furniture.description" placeholder="description">
        <input type="number" v-model="furniture.quantity" placeholder="quantity">
        <input type="number" v-model="furniture.price" placeholder="price">
        <button @click="publishFurnitureInfo()">Publish My Ad</button>
    </div>
</template>

<script>
    import api from "./backend-api";

    export default {
        name: "AddFurniture",
        data () {
            return {
                response: [],
                furniture: {
                    name: '',
                    img_url: '',
                    quantity: '',
                    description: '',
                    price: 0,
                }
            }
        },
        methods: {
            publishFurnitureInfo () {
                api.addFurniture(this.furniture).then(response => {
                    this.response = response.data;
                }).catch(e => {
                    this.errors.push(e)
                })
            }
        }
    }
</script>

<style scoped>

</style>