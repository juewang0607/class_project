<template>
    <div id="show-furniture">
<!--        <h1>All Furniture</h1>-->
        <div v-for="furniture in furniture_list" :key="furniture.id" class="single-furniture">
            <h2>{{ furniture.name }}</h2>
            <p>Quantity: {{ furniture.quantity }}</p>
            <p>Img URL: {{ furniture.img_url }} </p>
            <p>Description: {{ furniture.description }} </p>
            <p>Price: {{ furniture.price }} </p>
        </div>
    </div>
</template>

<script>
    import api from "./backend-api";

    export default {
        name: "ShowFurniture",

        data () {
            return {
                response: [],
                furniture_list: [],
            }
        },
        created() {
            api.showAllFurniture().then(response => {
                console.log(response);
                this.furniture_list = response.data;
            })
        }
    }
</script>

<style scoped>
    #show-furniture {
        max-width: 800px;
        margin: 0 auto;
    }
    .single-furniture{
        padding: 20px;
        margin: 20px 0;
        box-sizing: border-box;
        background: #eee;
    }
</style>