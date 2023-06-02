import Image from "next/image";

function importAll(r) {
    return r.keys().map(r);
}

export default function BackgroundFiller({}) {

    let images = importAll(require.context('../../public/pngs/', false, /\d\.(png|jpe?g|svg)$/));


    let nb = images.length
    return (
        <div className="fixed h-full w-full" style={{zIndex:'-1'}}>
            {images.map((image,index) => {return (<Image src={image} alt="img0" className="h-40 w-40 z-0" style={{zIndex:'-1',position: 'absolute',transform: 'translate('+Math.random()*90+'vw, '+(index+1)/nb*75+'vh) rotate('+Math.random()*90+'deg)',}} />)})}
        </div>
    )
}