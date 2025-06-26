export default function HomeHeader() {
    return (
        <header className="flex min-w-screen h-20 bg-gray-400 items-center content-center justify-center drop-shadow-md">
            <div className="flex min-w-5xl items-center content-center justify-around">
                <p className="font-bold text-white text-2xl drop-shadow-md">CarServiceRate</p>
                <div className="flex gap-2">
                <button className="bg-amber-50 p-4 rounded-full w-40 drop-shadow-md">Login</button>
                <button className="bg-amber-50 p-4 rounded-full w-40 drop-shadow-md">Register</button>
                </div>
            </div>
        </header>
    )
}