<template>
    <div class="enter form-box">
        <div class="header">Register</div>
        <div class="body">
            <form @submit.prevent="onRegister">
                <div class="field">
                    <div class="name">
                        <label for="login">Login</label>
                    </div>
                    <div class="value">
                        <input autofocus id="login" name="login" v-model="login"/>
                    </div>
                </div>
                <div class="field">
                    <div class="name">
                        <label for="name_user">Our name</label>
                    </div>
                    <div class="value">
                        <input id="name_user" name="name_user" type="name_user" v-model="name_user"/>
                    </div>
                </div>
                <div class="field error">{{ error }}</div>
                <div class="button-field">
                    <input type="submit" value="Register">
                </div>
            </form>
        </div>
    </div>
</template>

<script>
export default {
    name: "Register",
    data: function () {
        return {
            login: "",
            name_user: "",
            error: ""
        }
    },
    methods: {
        onRegister: function () {
            this.error = "";
            this.$root.$emit("onRegisterUser", this.login, this.name_user);
        }
    },
    beforeMount() {
        this.$root.$on("onRegisterValidationError", error => this.error = error);
    }
}
</script>

<style scoped>

</style>