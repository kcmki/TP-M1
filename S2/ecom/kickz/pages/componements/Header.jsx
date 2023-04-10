
import {BagIcon,User,Loupe,Logo} from './Utils/svgs'
import Image from 'next/image'

export default function Header() {

    let running = ["https://media.cntraveler.com/photos/62e7cbd471f6982b50a34949/master/w_2100,h_1500,c_limit/Best%20Running%20Shoes-2022_Asics%20Gel-Contend%206%20Women.jpg",
    "https://images.stockx.com/images/adidas-Ultra-Boost-4-0-Triple-White-W-Product.jpg?fit=fill&bg=FFFFFF&w=700&h=500&fm=webp&auto=compress&q=90&dpr=2&trim=color&updated_at=1612901004"]
    let lifestyle = ["https://images.stockx.com/images/Adidas-Yeezy-Boost-350-V2-Core-Black-Red-Product.jpg?fit=fill&bg=FFFFFF&w=700&h=500&fm=webp&auto=compress&q=90&dpr=2&trim=color&updated_at=1606319240",
    "https://images.stockx.com/images/Nike-Air-Force-1-Low-White-07_V2-Product.jpg?fit=fill&bg=FFFFFF&w=700&h=500&fm=webp&auto=compress&q=90&dpr=2&trim=color&updated_at=1631122839"]

    const imgLoader = ({ src }) => {return src}
    return (
        <header className="bg-black w-full h-min rounded-3xl text-white flex flex-row justify-between z-50 sticky ">

            <div className="flex justify-between items-start w-1/4 h-24 px-8 ">
                <div className="flex items-center h-24 w-full"> <Logo /> </div>
            </div>

            <div className="flex justify-around w-2/4 px-8 font-semibold flex-wrap ">
                <div className="flex items-center h-24" ><a href=""><h1>Home</h1></a></div>
                <div className="flex items-center running h-24"><a href=""> <h1>Running</h1></a></div>
                <div className="flex items-center lifestyle h-24"><a href=""> <h1>Lifestyle</h1></a></div>
                <div className="flex items-center h-24"><a href=""> <h1>About</h1> </a></div>
                <div className="running-dropper w-full h-0 transition-height opacity-0 flex flex-col flex-wrap">
                    <div className="latest w-1/2 h-full  p2 flex flex-col justify-center items-center">
                        <h2 className='text-l h-1/6'>Latest</h2>
                        <a href="" className="flex flex-col h-5/6 items-center p-1 m-2 rounded justify-center hover:bg-white hover:text-black transition">
                            <Image loader={imgLoader} src={running[0]} alt="Picture of the author" width={10} height={10} className='rounded h-full w-auto 	'/>
                        </a>
                    </div>  
                    <div className="trending w-1/2 h-full  p-2 flex flex-col justify-center items-center">
                        <h2 className='text-l h-1/6'>trending</h2>
                        <a href="" className="flex flex-col h-5/6 items-center p-1 m-2 rounded justify-center hover:bg-white hover:text-black transition">
                            <Image loader={imgLoader} src={running[1]} alt="Picture of the author" width={10} height={10}className='rounded h-full w-auto 	'/>
                        </a>
                    </div>  
                </div>
                <div className="lifestyle-dropper w-full h-0 transition-height opacity-0 flex flex-col flex-wrap">
                    <div className="latest w-1/2 h-full  p2 flex flex-col justify-center items-center">
                        <h2 className='text-l h-1/6'>Latest</h2>
                        <a href="" className="flex flex-col h-5/6 items-center p-1 m-2 rounded justify-center hover:bg-white hover:text-black transition">
                            <Image loader={imgLoader} src={lifestyle[0]} alt="Picture of the author" width={10} height={10} className='rounded h-full w-auto 	'/>
                        </a>
                    </div>  
                    <div className="trending w-1/2 h-full  p-2 flex flex-col justify-center items-center">
                        <h2 className='text-l h-1/6'>trending</h2>
                        <a href="" className="flex flex-col h-5/6 items-center p-1 m-2 rounded justify-center hover:bg-white hover:text-black transition">
                            <Image loader={imgLoader} src={lifestyle[1]} alt="Picture of the author" width={10} height={10} className='rounded h-full w-auto 	'/>
                        </a>
                    </div>  
                </div>
            </div>

            <div className="flex justify-end w-1/4 px-8 pl-20 flex-wrap ">
                <div className='flex flex-row h-24'>
                    <div className="flex justify-center items-center p-4"><Loupe /></div>
                    <div className="flex justify-center items-center p-4"><User /></div>
                    <div className="flex justify-center items-center p-4"><BagIcon /></div>
                </div>


                <input type="checkbox" name="Bag" id="Bag" className='hidden'/>
                <Bag />

            </div>

        </header>
    )
}

function Bag({items}){
    items = [{image: "https://images.stockx.com/images/adidas-Ultra-Boost-4-0-Triple-White-W-Product.jpg?fit=fill&bg=FFFFFF&w=700&h=500&fm=webp&auto=compress&q=90&dpr=2&trim=color&updated_at=1612901004", nom: "Ultra Boost", price: 200},
    {image: "https://images.stockx.com/images/adidas-Ultra-Boost-4-0-Triple-White-W-Product.jpg?fit=fill&bg=FFFFFF&w=700&h=500&fm=webp&auto=compress&q=90&dpr=2&trim=color&updated_at=1612901004", nom: "Ultra Boostttttttttttttttttttttt", price: 200},
    {image: "https://images.stockx.com/images/adidas-Ultra-Boost-4-0-Triple-White-W-Product.jpg?fit=fill&bg=FFFFFF&w=700&h=500&fm=webp&auto=compress&q=90&dpr=2&trim=color&updated_at=1612901004", nom: "Ultra Boostttttttttttttttttttttt", price: 200}]
    return(
        <div className='bag-dropper w-full h-0 opacity-0 '>
            <form action="" method="post" className='flex flex-col items-center text-black'>
                <div className='flex flex-col h-36 overflow-y-scroll scrollbar scrollbar-rounded-sm scrollbar-thumb-gray-200 scrollbar-track-black scrollbar-thin '>
                {
                    items.map((item) => <BagItem image={item.image} name={item.nom} price={item.price} />)
                }
                </div>
                <input type="button" value="Checkout" className='bg-blue-500 hover:bg-blue-700 text-white font-bold py-2 px-4 rounded-full' />
            </form>
        </div>
    )
}

function BagItem({image, name, price}){
    return(
        <div key={name} className='flex text-white'>
            <img src={image} alt="Picture of the author" className='rounded h-12 w-auto'/>
            <p className='text-basic overflow-hidden text-break'>{name}</p>
            <div>
                <p className='text-center font-semibold'>{price}</p>
                <input type="number" max="10" min="0" defaultValue="1"  name="name" id="name"  className='bg-white text-black focus:outline-none focus:ring-2 focus:ring-blue-600 focus:border-transparent rounded-full w-10 h-6 p-2 m-2' />
            </div>
        </div>
    )
}