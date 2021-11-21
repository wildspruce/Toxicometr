<template>
  <v-row class="pt-3 pb-3 pr-6">
    <v-spacer v-if="!this.source.isleft"></v-spacer>
    <v-spacer v-if="!this.source.isleft"></v-spacer>
  <v-col md-3 sm-3 xs-3 lg-3 xl-3>
    <v-card class="elevation-5 px-1" style="background-color: #1a222c; border-radius: 25px">
      <h3 class="mr-3 mt-3" style="color: aliceblue; text-align: end">{{this.source.content.author}}</h3>
      <v-card-title class="form-label justify-start">
        <div style="color: white" v-html="getSource()"/>
      </v-card-title>
    </v-card>
  </v-col>
    <v-spacer v-if="this.source.isleft"></v-spacer>
    <v-spacer v-if="this.source.isleft"></v-spacer>
  </v-row>
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
      gen: "",
      startOffset: 0,
      imageBase: '<img style="width: 50px; position: relative; top: 10px" src="" alt="">'
    }
  },
  mounted() {
  },
  methods: {
    getSource() {
      return this.source.content.replacement
    }
  }
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