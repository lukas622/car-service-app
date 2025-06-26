export default function Home() {
    return (
    <>
      <HomeHeader/>
      <main className='flex flex-col min-w-screen min-h-screen bg-cyan-50 text-center items-center'>
        <div className='p-10 bg-amber-50 rounded-full  mt-30'>
          <p className='font-bold text-black'>CarServiceRate is a platform which allows you to view detailed information about car service technicians.</p>
          <br />
          <p className='font-bold text-black'>Register to become a part of this community</p>
        </div>
      </main>
    </>
    )
}