import Api from "./api"

export default {
    getContent(){
        return Api().get("/")
    }
}