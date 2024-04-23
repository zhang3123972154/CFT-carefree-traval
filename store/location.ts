import { defineStore } from "pinia";

interface Location {
    country: string,
    province: string,
    city: string,
    district: string,
    street: string,
    streetum: string,
    poiName: string,
    cityCode: string
}

export const useLocation = defineStore("locationStore", {
    state: (): {
        location: Location
    } => {
        return {
            location: {
                country: "中国",
                province: "定位中",
                city: "定位中",
                district: "定位中",
                street: "定位中",
                streetum: "定位中",
                poiName: "定位中",
                cityCode: "000",
            }
        }
    },
    getters: {
        city(state) {
            const regex = /(.+?)(市)/;
            const match = state.location.city.match(regex);

            if(match)
                return match[1];
            else
                return state.location.city;
        }
    },
    actions: {
        getLocation() {
            uni.getLocation({ // info 适用 App， H5 不行。
                type: 'gcj02',
                geocode: true,
                success: ({ longitude, latitude, address }) => {
                    // console.info("test-location", address);
                    this.location = address;
                },
                fail: (error) => {
                    console.error(error);
                }
            });
        }
    }
})