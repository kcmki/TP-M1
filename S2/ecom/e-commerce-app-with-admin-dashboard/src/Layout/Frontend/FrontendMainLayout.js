import React from 'react'
import { Outlet } from 'react-router-dom'
import Navbar from './Navbar'

const FrontendMainLayout = () => {
  return (
    <>
        <BackgroundFiller/>
        <Navbar />
        <div>
            <Outlet />
        </div>
    </>
  )
}


function importAll(r) {
    return r.keys().map(r);
  }

function BackgroundFiller({}) {
    let images = importAll(require.context('../../../public/pngs/', false, /\d\.(png|jpe?g|svg)$/));

    let nb = images.length
    return (
        <div className="fixed h-full w-full" style={{zIndex:'-1',height:"100vh",width:"100w",position:"fixed"}}>
            {images.map((image,index) => {return (<img src={image} alt="img0" className="h-40 w-40 z-0" style={{height:"150px",width:"150px",zIndex:'-1',position: 'absolute',transform: 'translate('+Math.random()*90+'vw, '+(index+1)/nb*80+'vh) rotate('+Math.random()*90+'deg)',}} />)})}
        </div>
    )
}

export default FrontendMainLayout