<template>
	<block v-if="prop.show">
		<view class="maker"></view>
		<view class="popup">
			<view class="head-box">
				<text class="iconfont icon-fanhuijiantou" @click="onBack"></text>
				<text class="title">选择城市</text>
				<text class="iconfont icon-quxiao" @click="$emit('update:show', false)"></text>
			</view>
			<block v-if="isCity">
				<view class="province-box">
					<text class="fullname">{{ province.fullname }}</text>
					<text class="text">请选择</text>
				</view>
				<scroll-view scroll-y="true" class="province-scroll-view">
					<view class="province">
						<view class="ul">
							<block v-for="(item, index) in cityList" :key="index">
								<view class="li" @click="handlerCity(item)">{{ item.name }}</view>
							</block>
						</view>
					</view>
				</scroll-view>
			</block>
			<block v-else>
				<scroll-view scroll-y="true" class="scroll-view">
					<view class="location" @click="handlerCity(currentCity, '1')">
						<text class="iconfont icon-dingwei"></text>
						<text>当前定位城市</text>
						<text class="city">{{ currentCity?.name || '获取位置失败' }}</text>
					</view>
					<view class="popular-city">
						<view class="title">热门城市</view>
						<view class="ul">
							<block v-for="(item, index) in popularCitieList" :key="index">
								<view class="li" @click="handlerCity(item, '1')">
									{{ item.name }}
								</view>
							</block>
						</view>
					</view>
					<view class="province">
						<view class="ul">
							<block v-for="(item, index) in addressList[0]" :key="index">
								<view class="li" @click="handlerProvince(item)">
									{{ item.name }}
								</view>
							</block>
						</view>
					</view>
				</scroll-view>
			</block>
		</view>
	</block>
</template>

<script lang="ts" setup>
import { ref, withDefaults, defineProps } from 'vue'
import addressList from './config.ts'
const prop = withDefaults(defineProps<{ show: boolean; popularCities?: string[]; currentCity?: string }>(), {
	show: false,
	popularCities: ['北京', '天津', '上海', '深圳', '西安', '郑州', '运城'], // 热门城市
	currentCity: '运城市'
})
const $emit = defineEmits(['update:show', 'onSelectCity'])
const isCity = ref(false)
const province = ref() // 省
const city = ref() // 市
const cityList = ref([])
const currentCity = ref() // 获取当前城市
const popularCitieList = ref([])
watch(
	() => prop.show,
	val => {
		if (val) {
			getCurrentCity()
			getPopularCities()
		}
	}
)
function handlerProvince(val: {
	id: string
	name: string
	fullname: string
	pinyin: string[]
	location: {
		lat: number
		lng: number
	}
	cidx: number[]
}) {
	if (val.fullname[val.fullname.length - 1] == '市' || ['710000', '810000', '820000'].includes(val.id)) {
		province.value = null
		city.value = val
		handlerCity(val)
	} else {
		province.value = val
		getCity()
		isCity.value = true
	}
}
function getCity() {
	const code = province.value.id.slice(0, 2)
	const citys = addressList[1].filter(item => item.id.startsWith(code))
	cityList.value = citys
}
function onBack() {
	isCity.value = false
	city.value = null
	province.value = null
	cityList.value = []
}
// 选择城市
function handlerCity(city: any, type?: number) {
	let result: any = { detail: { province: null, city } }
	if (type && type == 1) {
		if (province.value) {
			result.province = province.value
		} else {
			const code = city.id.slice(0, 2)
			const city_0 = addressList[0].find(item => [code + '0000'].includes(item.id))
			result.detail.province = city_0
		}
	} else {
		city.value = city
		result.detail.province = province.value || city
	}
	isCity.value = false
	$emit('update:show', false)
	$emit('onSelectCity', result)
}
// 获取当前城市
function getCurrentCity() {
	const city_0 = addressList[0].find(item => [prop.currentCity].includes(item.fullname))
	const city_1 = addressList[1].find(item => [prop.currentCity].includes(item.fullname))
	currentCity.value = city_0 ? city_0 : city_1
}
// 热门城市
function getPopularCities() {
	const city_0 = addressList[0].filter(item => prop.popularCities.includes(item.name))
	const city_1 = addressList[1].filter(item => prop.popularCities.includes(item.name))
	popularCitieList.value = [...city_0, ...city_1]
}
</script>

<style lang="scss" scoped>
.maker {
	width: 100vw;
	height: 100vh;
	position: fixed;
	left: 0;
	right: 0;
	bottom: 0;
	background-color: #000000;
	opacity: 0.8;
	z-index: 99;
}
.popup {
	width: 100%;
	height: calc(100% - 280rpx - env(safe-area-inset-bottom));
	position: fixed;
	left: 0;
	right: 0;
	bottom: 0;
	background-color: #fff;
	border-top-left-radius: 30rpx;
	border-top-right-radius: 30rpx;
	border-top: 2rpx solid #000000;
	z-index: 1000;
	opacity: 1;
	.head-box {
		padding: 38rpx;
		box-sizing: border-box;
		width: 100%;
		display: flex;
		align-items: center;
		justify-content: space-between;
		.title {
			font-size: 32rpx;
			font-weight: bold;
		}
		.iconfont {
			font-size: 46rpx;
			color: #888888;
			display: block !important;
		}
	}
	.scroll-view {
		width: 100%;
		height: calc(100% - 150rpx);
	}
	.location {
		width: 100%;
		font-size: 30rpx;
		display: flex;
		align-items: center;
		padding: 20rpx 38rpx;
		box-sizing: border-box;
		border-bottom: 6rpx solid #f8f8f8;
		.iconfont {
			font-size: 42rpx;
			font-weight: 500;
			color: #888;
			margin-right: 10rpx;
			display: block !important;
		}
		.city {
			margin-left: 20rpx;
			font-weight: 500;
		}
	}
	.popular-city {
		width: 100%;
		padding: 20rpx 38rpx;
		box-sizing: border-box;
		.title {
			font-size: 28rpx;
			color: #888;
		}
		.ul {
			width: 100%;
			display: flex; /* flex布局 */
			justify-content: flex-start; /* 左对齐 */
			flex-wrap: wrap; /* 换行 */
			gap: 18rpx;
			margin-top: 20rpx;
			.li {
				padding: 10rpx 30rpx;
				box-sizing: border-box;
				border: 1rpx solid #ccc;
				text-align: center;
				flex: 1;
				width: 23%;
				min-width: 23%; /* 加入这两个后每个item的宽度就生效了*/
				max-width: 23%; /* 加入这两个后每个item的宽度就生效了*/
				overflow: hidden;
				font-size: 28rpx;
				white-space: nowrap;
				overflow: hidden;
				text-overflow: ellipsis;
				&:nth-child(1) {
					margin-left: 0;
				}
				&:nth-of-type(5n + 0) {
					margin-left: 0;
				}
			}
		}
	}
	.province {
		width: 100%;
		padding: 0 38rpx;
		box-sizing: border-box;
		.ul {
			display: flex;
			flex-direction: column;
			.li {
				width: 100%;
				line-height: 80rpx;
				border-bottom: 4rpx solid #f8f8f8;
				font-size: 28rpx;
			}
		}
	}
	.province-box {
		width: 100%;
		padding: 0 38rpx;
		display: flex;
		align-items: center;
		.fullname {
			font-size: 32rpx;
			font-weight: 500;
		}
		.text {
			display: inline-block;
			font-size: 28rpx;
			color: #f56f4f;
			margin-left: 30rpx;
			position: relative;
			&:before {
				content: '';
				width: 100%;
				position: absolute;
				right: 0;
				left: 0;
				bottom: 0;
				margin: auto;
				height: 4rpx;
				border-radius: 4rpx;
				background-color: #f56f4f;
			}
		}
	}
	.province-scroll-view {
		width: 100%;
		margin-top: 20rpx;
		height: calc(100% - 200rpx - env(safe-area-inset-bottom));
	}
}
</style>
