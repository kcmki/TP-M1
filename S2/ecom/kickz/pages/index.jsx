

import { Inter } from 'next/font/google'
import { useState } from 'react'
import Header from './componements/Header.jsx'
import basket from '../public/basket.png'
import Image from 'next/image.js'
import "slick-carousel/slick/slick.css";

import "slick-carousel/slick/slick-theme.css";

import Slider from "react-slick";


const inter = Inter({ subsets: ['latin'] })

export default function Home({}) {


  const [Bag,setBag] = useState("myBag")


  return (
    <main className={inter.className+ "h-screen w-screen py-12 px-16 2xl:px-40 overflow-x-hidden"} >
      
      <Header />
      <Presentation />
      <NewProds />
    </main>
  )
}

function Presentation(){
  return (
    <div className="flex flex-col justify-center items-center w-full my-20 z-10">
      <h1 className="text-5xl font-bold drop-shadow-[0_1.2px_1.2px_rgba(0,0,0,0.8)]">Design  & Quality </h1>
      <h2 className="text-2xl">Your "sneakers" world</h2>
      <Image src={basket} alt="Basket" className='rounded h-full w-auto 	'/>
    </div>
  )
}

function NewProds(){
  var settings = {
    dots: true,
    infinite: true,
    speed: 500,
    slidesToShow: 3,
    slidesToScroll: 1
  };
  let newProducts = [{link:"",img:"https://contents.mediadecathlon.com/p2155572/k$82c53deb06b3aa4145acc69227e616c9/sq/8544265.jpg?format=auto&f=800x0",nom:"Nike Air Max 270",prix:100},
  {link:"",img:"https://assets.laboutiqueofficielle.com/w_210,q_auto,f_auto/media/products/2021/05/31/reebok_268771_FAB_GW8357_TPDT_20210615T073244_01.jpg",nom:"Nike Air Max 270",prix:100},
  {link:"",img:"https://contents.mediadecathlon.com/p2155572/k$82c53deb06b3aa4145acc69227e616c9/sq/8544265.jpg?format=auto&f=800x0",nom:"Nike Air Max 270",prix:100},
  {link:"",img:"https://contents.mediadecathlon.com/p2155572/k$82c53deb06b3aa4145acc69227e616c9/sq/8544265.jpg?format=auto&f=800x0",nom:"Nike Air Max 270",prix:100},
  {link:"",img:"https://contents.mediadecathlon.com/p2155572/k$82c53deb06b3aa4145acc69227e616c9/sq/8544265.jpg?format=auto&f=800x0",nom:"Nike Air Max 270",prix:100},
  ]
  return(
  <div className='w-full h-82 bg-black shadow px-64 py-12 rounded-xl'>  
    <h1 className="text-4xl font-bold text-white text-center pb-10">New Products</h1>
  <Slider {...settings}>
    {
      newProducts.map((prod)=>{
        return(
          <a href={prod.link} className="flex flex-col items-center justify-center h-64 w-24 text-white hover:bg-white hover:text-black transition rounded-xl">
            <div className='flex justify-center items-center h-3/5'><img src={prod.img} alt='tt' className="block rounded-xl h-full  w-auto" /></div>
            <div className="flex flex-col justify-center items-center h-2/5 w-full">
              <h1 className="text-xl font-bold">{prod.nom}</h1>
              <h2 className="text-normal">{prod.prix}$</h2>
            </div>
          </a>
        )
      })
    }

    </Slider>
  </div>
  )

}