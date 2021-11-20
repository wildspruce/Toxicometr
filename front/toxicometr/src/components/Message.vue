<template>
  <v-col md-4 sm-6 xs-12 lg-4 xl-4>
    <v-card class="elevation-5 px-4">
      <v-card-title class="form-label justify-center">
        <div v-html="getSource()"/>
      </v-card-title>
    </v-card>
  </v-col>
</template>

<script>
export default {
  name: "MessageComponent",
  props: {
    index: { // index of current item
      type: Number
    },
    source: { // here is: {uid: 'unique_1', text: 'abc'}
      type: Object,
      default () {
        return {}
      }
    }
  },
  data() {
    return {
      gen: '<b>'+ this.source.text + '</b><component-x></component-x>'
    }
  },
  mounted() {
  },
  methods: {
    getSource() {
      console.log("Message - " + this.source.text)
      return this.gen
    }
  },
}
</script>

<style scoped>
.form-label {
  color: black;
  font-size: 32px;
  white-space: nowrap;
  font-family: Century Gothic, CenturyGothic, AppleGothic, sans-serif;
}
.container{
  position: relative;
  width: 400px;
  height: 50px;

}
.box{
  width: 100%;
  height: 100%;
  display: inline-block;
  position: absolute;
  outline: 2px solid;
  top: 0;
  left: 0;
}
#stack-top{
  background-color: #E85656;
}
</style>