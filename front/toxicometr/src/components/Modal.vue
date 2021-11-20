<template>
  <transition name="modal-fade">
    <div class="modal-backdrop " >
      <div class="modal">
        <div class="modal-header">
        </div>
        <div style="height: 180px; background: linear-gradient(#1d081d, #191727); "></div>
        <div class="modal-body"  style="background: linear-gradient(#181728, #360a2b); ">
          <v-card-title class="justify-center form-label" >Name yourself!</v-card-title>
          <v-text-field
              dark
              full-width
              class="name ml-10 mr-10"
              ref="name"
              v-model="name"
              style="color: #f3f3f3; font-size: medium"
              :rules="[n => !!n || 'Name can not be empty!']"
              dense
              filled
          ></v-text-field>
          <div class="ok-button" @click="close">
            <h1>OK</h1>
          </div>
        </div>
      </div>
    </div>
  </transition>
</template>

<script>
export default {
  name: 'Modal',
  data() {
    return {
      name: null,
    }
  },
  methods: {
    close() {
      if (!this.$refs['name'].hasError)
      this.$emit('changeUsername', this.name);
    },
  },
};
</script>

<style scoped>
.ok-button{
  width: 200px;
  display: inline-block;
  text-align: center;
  height: 50px;
  border: 2px solid white ;
  background-color: #0d1426;
  border-radius: 7px
}
.ok-button:hover{
  background-color: #080d1d;
}

h1{
  color: white;
  font-size: 26px;
  font-family: Century Gothic, CenturyGothic, AppleGothic, sans-serif;
  cursor: pointer;
}
.modal-backdrop {
  position: fixed;
  z-index: 9;
  top: 0;
  bottom: 0;
  left: 0;
  right: 0;
  background-color: rgba(0, 0, 0, 0.3);
  display: flex;
  justify-content: center;
  align-items: center;
}

.modal {
  /*background-image: url("assets/images/Alert_bg.png");*/
  background-size: contain;
  width: 834px;
  height: 457px;
  left: 200px;
  top: 132px;
  box-shadow: 2px 2px 20px 1px;
  display: inline-block;
}


.modal-header,
.modal-footer {
  padding: 15px;
  display: flex;
}

.modal-header {
  position: relative;
  color: #4AAE9B;
  justify-content: space-between;
}

.v-card-title {
  FONT-VARIANT: JIS78;
  align-items: center;
  display: flex;
  flex-wrap: wrap;
  font-size: 1.8rem;
  font-weight: 500;
  letter-spacing: .0125em;
  line-height: 2rem;
  word-break: break-all;
}
.modal-body {
  text-align: center;
  position: relative;
  padding: 20px 10px;
}

.btn-close {
  position: absolute;
  top: -100%;
  right: -1%;
  border: none;
  font-size: 40px;
  padding: 10px;
  cursor: pointer;
  font-weight: bold;
  color: '360A2BFF';
  background: transparent;
}

.btn-green {
  color: white;
  background: #4AAE9B;
  border: 1px solid #4AAE9B;
  border-radius: 2px;
}
.modal-fade-enter,
.modal-fade-leave-to {
  opacity: 0;
}

.modal-fade-enter-active,
.modal-fade-leave-active {
  transition: opacity .5s ease;
}
.form-label{
  font-style: normal;
  font-weight: normal;
  font-size: 48px;
  line-height: 50px;
  color: white;
}
</style>