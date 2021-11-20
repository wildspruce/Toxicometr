<template>
  <v-col md-4 sm-6 xs-12 lg-4 xl-4>
    <v-card class="elevation-5 px-4" style="background-color: #1a222c">
      <v-card-title class="form-label justify-center">
        <div style="color: white" v-html="getSource()"/>
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
      gen: '<b>'+ this.source.text + '<img style="width: 50px; position: relative; top: 10px" src="https://emojipedia-us.s3.dualstack.us-west-1.amazonaws.com/thumbs/144/apple/285/grinning-face-with-smiling-eyes_1f604.png" alt=";)">'+
          '</b><component-x></component-x>'
    }
  },
  mounted() {
  },
  methods: {
    getSource() {
      console.log("Message - " + this.source.text)
      return this.gen
    },
    // prepareMessage(start, end, image) {
    //
    // }
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
</style>